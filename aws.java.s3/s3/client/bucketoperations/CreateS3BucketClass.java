package s3.client.bucketoperations;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

import s3.connect.AWSConnection;

public class CreateS3BucketClass {

	AmazonS3 s3BucketClientObj = AWSConnection.connection(); // This object has the connection details for connecting to
																// the AWS account.

	File file = null;

	public Bucket createNewS3Bucket(String bucketName) { // Method to create a new S3 Bucket
		Bucket bucket = null;
		if (s3BucketClientObj.doesBucketExist(bucketName)) {
			System.out.println("Bucket name entered already exists, Please try another Unique bucket name");
			return null;
		} else
			bucket = s3BucketClientObj.createBucket(bucketName);

		return bucket; // Returns the S3 bucket Object
	}

	// Upload Objects to S3 bucket
	public void uploadObjectsToS3Bucket(String bucketName) {
		if (s3BucketClientObj.doesBucketExist(bucketName)) { // Checks if Bucket exists or not

			try {

				System.out.println("Enter the file path to upload");
				Scanner scanner = new Scanner(System.in);
				String filePath = scanner.next();  // Path of the File to be Uploaded
				String keyName = Paths.get(filePath).getFileName().toString();
				
				System.out.println("Uploading the Objects to the S3 bucket:\t" + bucketName);
				
				s3BucketClientObj.putObject(bucketName, keyName, new File(filePath));  // This will upload the file to the bucket
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			createNewS3Bucket(bucketName); // Creates a new bucket with the provided name if Bucket NOT Found
			System.out.println("A new bucket created as the bucket didnt existed");

			uploadObjectsToS3Bucket(bucketName); // A recursive function to start the process of Uploading Objects to
													// the specified Bucket

		}
	}

}
