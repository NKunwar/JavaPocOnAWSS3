package s3.test;

import s3.client.bucketoperations.ListS3BucketClass;

/* A simple Class to test all the implemented classes & methods in the package s3.client.bucketoperations */

public class TestAllImplementedFunctions {

	public static void main(String[] args) {
		
//		AmazonS3 s3BucketClientObj = AWSConnection.connection(); // This object has the connection details for connecting to the AWS account.
		
		ListS3BucketClass lsc = new ListS3BucketClass();
		System.out.println(lsc.listAllS3Buckets());

	}

}
