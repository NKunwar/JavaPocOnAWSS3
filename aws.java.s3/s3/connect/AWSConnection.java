package s3.connect;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AWSConnection {

	public static AmazonS3 connection() {
		AWSCredentials creds = null;
		AmazonS3 s3clientObj = null;
		try {
			creds = new BasicAWSCredentials("AKIAIVISL75M3FPSELGQ", "UcbhVsPYxEMvxyKUeUUJq6bbRNif+cZymqZ+QQFt");

			s3clientObj = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(creds))
					.withRegion(Regions.US_EAST_1).build();

			System.out.println("creds & s3client Object created successfully");

		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		} catch (SdkClientException sce) {
			sce.printStackTrace();
		}
		return s3clientObj;
	}
}