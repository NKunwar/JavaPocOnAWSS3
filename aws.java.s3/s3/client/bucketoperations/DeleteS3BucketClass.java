package s3.client.bucketoperations;

import java.util.List;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import s3.connect.AWSConnection;

public class DeleteS3BucketClass {

	AmazonS3 s3BucketClientObj = AWSConnection.connection(); // This object has the connection details for connecting to the AWS account.

	// Delete a Empty AWS S3 Bucket
	public String deleteS3EmptyBucket(String bucketName) {
		try {
			if (s3BucketClientObj.doesBucketExist(bucketName)) { // Checks if Bucket name exists
				s3BucketClientObj.deleteBucket(bucketName); // Deletes the Empty Bucket

				return "Bucket deleted";
			}
		} catch (Exception e) { // Will come here if bucket is not empty
			e.printStackTrace();
			return "Bucket not deleted as it was NOT empty";
		}
		return "Done with delete bucket code";
	}

	// Delete a Non-Empty AWS S3 Bucket
	public void deleteNonEmptyBucket(String bucketName) {
		if (deleteBucketObjects(bucketName) == true) {
			s3BucketClientObj.deleteBucket(bucketName);
		}
	}

	// Delete AWS S3 Bucket OBJECTS
	public boolean deleteBucketObjects(String bucketName) {
		if (s3BucketClientObj.doesBucketExist(bucketName)) {
			ObjectListing ol = s3BucketClientObj.listObjects(bucketName);
			List<S3ObjectSummary> listSummary = ol.getObjectSummaries();

			for (S3ObjectSummary sos : listSummary) {
				s3BucketClientObj.deleteObject(bucketName, sos.getKey());
			}
			return true;
		}
		return false;
	}

}