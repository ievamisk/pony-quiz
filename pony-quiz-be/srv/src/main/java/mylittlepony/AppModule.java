package mylittlepony;

import com.google.common.base.Predicates;
import restx.config.ConfigLoader;
import restx.config.ConfigSupplier;
import restx.factory.Provides;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import restx.security.*;
import restx.factory.Module;
import restx.factory.Provides;
import javax.inject.Named;

import java.nio.file.Paths;

@Module
public class AppModule {
    @Provides
    public SignatureKey signatureKey() {
         return new SignatureKey("-990669662645929310 mylittlepony MyLittlePony 04e7a810-88b7-4c9e-baaf-ed58850b5021".getBytes(Charsets.UTF_8));
    }

    @Provides
    @Named("restx.admin.password")
    public String restxAdminPassword() {
        return "2701";
    }

    @Provides
    public ConfigSupplier appConfigSupplier(ConfigLoader configLoader) {
        // Load settings.properties in mylittlepony package as a set of config entries
        return configLoader.fromResource("mylittlepony/settings");
    }

    @Provides
    public CredentialsStrategy credentialsStrategy() {
        return new BCryptCredentialsStrategy();
    }

    @Provides
    public BasicPrincipalAuthenticator basicPrincipalAuthenticator(
            SecuritySettings securitySettings, CredentialsStrategy credentialsStrategy,
            @Named("restx.admin.passwordHash") String defaultAdminPasswordHash, ObjectMapper mapper) {
        return new StdBasicPrincipalAuthenticator(new StdUserService<>(
                // use file based users repository.
                // Developer's note: prefer another storage mechanism for your users if you need real user management
                // and better perf
                new FileBasedUserRepository<>(
                        StdUser.class, // this is the class for the User objects, that you can get in your app code
                        // with RestxSession.current().getPrincipal().get()
                        // it can be a custom user class, it just need to be json deserializable
                        mapper,

                        // this is the default restx admin, useful to access the restx admin console.
                        // if one user with restx-admin role is defined in the repository, this default user won't be
                        // available anymore
                        new StdUser("admin", ImmutableSet.<String>of("*")),

                        // the path where users are stored
                        Paths.get("data/users.json"),

                        // the path where credentials are stored. isolating both is a good practice in terms of security
                        // it is strongly recommended to follow this approach even if you use your own repository
                        Paths.get("data/credentials.json"),

                        // tells that we want to reload the files dynamically if they are touched.
                        // this has a performance impact, if you know your users / credentials never change without a
                        // restart you can disable this to get better perfs
                        true),
                credentialsStrategy, defaultAdminPasswordHash),
                securitySettings);
    }
    @Provides
    public CORSAuthorizer getCORSAuthorizer() {
        return StdCORSAuthorizer.builder()
                .setAllowedHeaders(ImmutableSet.of("Content-Type",
                        "origin",
                        "accept",
                        "X-Requested-With"))
                .setOriginMatcher(Predicates.<CharSequence> alwaysTrue()) // We usually perform filtering here
                .setMaxAge(360000)
                .setPathMatcher(Predicates.<CharSequence> alwaysTrue())
                .setAllowedMethods(ImmutableSet.of("GET", "POST", "PUT", "HEAD", "DELETE"))
                .build();
    }
}
