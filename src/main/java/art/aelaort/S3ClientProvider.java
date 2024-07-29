package art.aelaort;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

public class S3ClientProvider {
	public static S3Client client(S3Params s3Params) {
		return client(s3Params.getId(), s3Params.getKey(), s3Params.getUrl(), s3Params.getRegion());
	}

	public static S3Client client(String id, String key, String url, String region) {
		AwsBasicCredentials credentials = AwsBasicCredentials.create(id, key);
		return S3Client.builder()
				.region(Region.of(region))
				.endpointOverride(URI.create(url))
				.credentialsProvider(StaticCredentialsProvider.create(credentials))
				.forcePathStyle(true)
				.build();
	}
}
