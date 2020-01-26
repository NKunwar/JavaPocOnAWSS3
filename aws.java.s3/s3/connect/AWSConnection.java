package s3.connect;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AWSConnection {
	
	public static AmazonS3 connection() {
		AWSCredentials creds = new BasicAWSCredentials("AKIAIVISL75M3FPSELGQ",
				"UcbhVsPYxEMvxyKUeUUJq6bbRNif+cZymqZ+QQFt");
		
		AmazonS3 s3clientObj = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(creds))
				.withRegion(Regions.US_EAST_1)
				.build();
		
		System.out.println("creds & s3client Object created successfully");
		
		return s3clientObj;
	}
	
	
}
