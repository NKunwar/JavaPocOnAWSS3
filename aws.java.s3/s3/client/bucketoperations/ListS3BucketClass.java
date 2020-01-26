package s3.client.bucketoperations;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import s3.connect.AWSConnection;

@SuppressWarnings("rawtypes")
public class ListS3BucketClass {

	AmazonS3 s3BucketClientObj = AWSConnection.connection(); // This object has the connection details for connecting to the AWS account.
	
	S3Object s3Object = null;
	
	// List All S3 Buckets
	public List listAllS3Buckets() {
		List<Bucket> list = s3BucketClientObj.listBuckets();
		List<String> listBucketNames = new ArrayList<>();
		for(Bucket buck: list) {
			listBucketNames.add(buck.getName());
		}
		return listBucketNames;
	}
	
	// List All the Objects of a particular S3 Bucket
	public List listAllBucketObjects(String bucketName) {
		List<S3Object> finalObjectList = new ArrayList<>();
		try {
			if(s3BucketClientObj.doesBucketExist(bucketName)) {
				ObjectListing images = s3BucketClientObj.listObjects(bucketName);
				
				List<S3ObjectSummary> listSummary = images.getObjectSummaries();
			
				for(S3ObjectSummary objSummary: listSummary) {
				   finalObjectList.add(s3BucketClientObj.getObject(bucketName, objSummary.getKey()));
				}		
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return finalObjectList;
	}
	
	// List out a particular object of S3 Bucket using S3Object
	public S3Object findSpecificObjectByObject(String bucketName, S3Object obj) {
		if(s3BucketClientObj.doesBucketExist(bucketName)) 
			return (s3BucketClientObj.getObject(bucketName, obj.getKey()));
		else return null;
	}
	
	// List out a particular object of S3 Bucket using S3Object KEY Name
	public S3Object findSpecificObjectByKey(String bucketName, String key) {
		if(s3BucketClientObj.doesBucketExist(bucketName)) 
			return (s3BucketClientObj.getObject(bucketName, key));
		else return null;
	}
}
