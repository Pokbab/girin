package com.cdg.girin.config;

//@Configuration
//@EnableSocial
//public class SocialConfig extends SocialConfigurerAdapter {
//    @Override
//    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        InMemoryUsersConnectionRepository repository = new InMemoryUsersConnectionRepository(connectionFactoryLocator);
//        repository.setConnectionSignUp(new QuickConnectionSignup());
//        return repository;
//    }
//    private static class QuickConnectionSignup implements ConnectionSignUp {
//        @Override
//        public String execute(Connection<!--?--> connection) {
//            return connection.getKey().getProviderUserId();
//        }
//    }
//    @Bean
//    public SignInAdapter signInAdapter() {
//        return new QuickSignInAdapter(new HttpSessionRequestCache());
//    }
//}