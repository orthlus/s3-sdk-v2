package art.aelaort;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.transfer.s3.S3TransferManager;

import java.net.URI;

public class S3ClientProvider {
	public static S3TransferManager transferManager(S3Params s3Params) {
		return transferManager(s3Params.getId(), s3Params.getKey(), s3Params.getUrl(), s3Params.getRegion());
	}

	public static S3TransferManager transferManager(String id, String key, String url, String region) {
		return S3TransferManager.builder()
				.s3Client(S3AsyncClient.builder()
						.region(Region.of(region))
						.endpointOverride(URI.create(url))
						.multipartEnabled(true)
						.credentialsProvider(getStaticCredentialsProvider(id, key))
						.forcePathStyle(true)
						.build())
				.build();
	}

	public static S3Client client(S3Params s3Params) {
		return client(s3Params.getId(), s3Params.getKey(), s3Params.getUrl(), s3Params.getRegion());
	}

	public static S3Client client(String id, String key, String url, String region) {
		return S3Client.builder()
				.region(Region.of(region))
				.endpointOverride(URI.create(url))
				.credentialsProvider(getStaticCredentialsProvider(id, key))
				.forcePathStyle(true)
				.build();
	}

	private static StaticCredentialsProvider getStaticCredentialsProvider(String id, String key) {
		return StaticCredentialsProvider.create(AwsBasicCredentials.create(id, key));
	}
}
