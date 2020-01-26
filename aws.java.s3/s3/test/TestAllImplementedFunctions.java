package s3.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import s3.client.bucketoperations.GetAllInfoOfABucket;

/* A simple Class to test all the implemented classes & methods in the package s3.client.bucketoperations */

public class TestAllImplementedFunctions {

	public static void main(String[] args) {
		
//		AmazonS3 s3BucketClientObj = AWSConnection.connection(); // This object has the connection details for connecting to the AWS account.
		
		GetAllInfoOfABucket gaiob = new GetAllInfoOfABucket();
		
//		System.out.println(gaiob.getAllInfo("bonevisabucket"));
		
//		List<Map> arrList = new ArrayList();
		 
		List<Map> arrList = gaiob.getInfoOfAllBuckets();
		for(Object obj: arrList) {
			System.out.println(obj.toString());
		}
		

	}

}
