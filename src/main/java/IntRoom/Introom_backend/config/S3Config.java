//package IntRoom.Introom_backend.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
//import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
//import software.amazon.awssdk.regions.Region;
//import software.amazon.awssdk.services.s3.S3Client;
//
//public class S3config {
//    @Configuration
//    public class S3Config {
//
//        @Bean
//        public S3Client s3Client() {
//            return S3Client.builder()
//                    .region(Region.AP_SOUTH_1) // use your region
//                    .credentialsProvider(
//                            StaticCredentialsProvider.create(
//                                    AwsBasicCredentials.create("AKIAZ3MGNCXOFFGC474M", "JmtAOL6IldlYpV8cYqZ1P8sKjpS680ng5uFYAlLP")
//                            )
//                    )
//                    .build();
//        }
//    }
//}

package IntRoom.Introom_backend.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class S3Config {

    @Value("${aws.access.key}")
    private String accessKey;

    @Value("${aws.secret.key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Bean
    public S3Client s3Client() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        return S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }

}




