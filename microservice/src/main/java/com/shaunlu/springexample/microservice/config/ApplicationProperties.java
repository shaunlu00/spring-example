package com.shaunlu.springexample.microservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final ApplicationProperties.Mail mail = new ApplicationProperties.Mail();

    public ApplicationProperties() {
    }

    public Mail getMail() {
        return this.mail;
    }

    public static class Mail {
        private String host = "";
        private String port = "";
        private String encode = "";
        private String sender_address = "";
        private String auth_user_name = "";
        private String auth_password = "";

        public Mail() {
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public String getPort() {
            return port;
        }

        public void setPort(String port) {
            this.port = port;
        }

        public String getEncode() {
            return encode;
        }

        public void setEncode(String encode) {
            this.encode = encode;
        }

        public String getSender_address() {
            return sender_address;
        }

        public void setSender_address(String sender_address) {
            this.sender_address = sender_address;
        }

        public String getAuth_user_name() {
            return auth_user_name;
        }

        public void setAuth_user_name(String auth_user_name) {
            this.auth_user_name = auth_user_name;
        }

        public String getAuth_password() {
            return auth_password;
        }

        public void setAuth_password(String auth_password) {
            this.auth_password = auth_password;
        }
    }
}
