package art.aelaort;

public class DefaultS3Params implements S3Params {
	private final String id;
	private final String key;
	private final String url;
	private final String region;

	public DefaultS3Params(String id, String key, String url, String region) {
		this.id = id;
		this.key = key;
		this.url = url;
		this.region = region;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getRegion() {
		return region;
	}
}
