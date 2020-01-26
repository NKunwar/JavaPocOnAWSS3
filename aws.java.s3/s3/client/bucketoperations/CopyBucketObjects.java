package s3.client.bucketoperations;

import java.util.List;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import s3.connect.AWSConnection;

public class CopyBucketObjects {
	
	AmazonS3 s3BucketClientObj = AWSConnection.connection();
	
	public void copyS3Objects(String sourceBucketName, String destBucketName) {
		
		if(s3BucketClientObj.doesBucketExist(sourceBucketName) && s3BucketClientObj.doesBucketExist(destBucketName)) {
			try {
				
			}
			catch(SdkClientException sce) {
				sce.printStackTrace();
			}
			
			ObjectListing objList = s3BucketClientObj.listObjects(sourceBucketName);
			List<S3ObjectSummary> objSum = objList.getObjectSummaries();
			int i = 1;
			for(S3ObjectSummary getObjSum: objSum) {
				s3BucketClientObj.copyObject(sourceBucketName, getObjSum.getKey(), destBucketName, String.valueOf(i++));
			}
			System.out.println("All objects from Bucket:\t" + sourceBucketName +  
					"\t have been successfully copied to bucket:\t" + destBucketName);
			
		} else System.out.println("Source/Destination Bukcket not created");
		
	}
	
}
