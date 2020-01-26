package s3.client.bucketoperations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;

import s3.connect.AWSConnection;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GetAllInfoOfABucket {

	AmazonS3 s3BucketClientObj = AWSConnection.connection();

	// Fetch all Info on a particular Bucket
	public List<Map> getAllInfo(String bucketName) {

		// Map<String, List> bucketNameMap = new HashMap();
		List<Map> listBucketMap = new ArrayList();

		try {
			
			Map<String, Object> bucketMap = new HashMap();
			Map<String, Map> distinguishBuckMap = new HashMap();
			
			if (s3BucketClientObj.doesBucketExist(bucketName)) {

				bucketMap.put("Bucket_Region_Name", s3BucketClientObj.getRegionName());
				bucketMap.put("Bucket_Location", s3BucketClientObj.getBucketLocation(bucketName));
				bucketMap.put("Bucket_Lifecycle_Configuration",
						s3BucketClientObj.getBucketLifecycleConfiguration(bucketName));
				bucketMap.put("Bucket_Acl", s3BucketClientObj.getBucketAcl(bucketName));
				bucketMap.put("Bucket_Accelerate_Configuration",
						s3BucketClientObj.getBucketAccelerateConfiguration(bucketName));
				bucketMap.put("Bucket_Cross_Origin_Configuration",
						s3BucketClientObj.getBucketCrossOriginConfiguration(bucketName));
				bucketMap.put("Bucket_Logging_Configuration", s3BucketClientObj.getBucketLoggingConfiguration(bucketName));
				bucketMap.put("Bucket_Notification_Configuration",
						s3BucketClientObj.getBucketNotificationConfiguration(bucketName));
				bucketMap.put("Bucket_Policy", s3BucketClientObj.getBucketPolicy(bucketName));
//				bucketMap.put("Bucket_Replication_Configuration", s3BucketClientObj.getBucketReplicationConfiguration(bucketName));
				bucketMap.put("Bucket_Tagging_Configuration", s3BucketClientObj.getBucketTaggingConfiguration(bucketName));
				bucketMap.put("Bucket_Website_Configuration", s3BucketClientObj.getBucketWebsiteConfiguration(bucketName));
				
				distinguishBuckMap.put(bucketName, bucketMap);
				
			}
			
			listBucketMap.add(distinguishBuckMap);
			
		} catch (AmazonS3Exception ase) {
			System.out.println(ase);
		}
		catch (SdkClientException sce) {
			System.out.println(sce);
		}

		// bucketNameMap.put(bucketName, listBucketMap);

		return listBucketMap;
	}

	// Fetch all Info of All Buckets
	public List<Map> getInfoOfAllBuckets() {

		List<Map> bucketInfoList = new ArrayList();
		Map<String, List> bucketsMap = new HashMap();

		List<Bucket> listBuckets = s3BucketClientObj.listBuckets();

		for (Bucket bucketName : listBuckets) {
			// bucketsMap.put(bucketName.toString(), getAllInfo(bucketName.toString()));
			bucketsMap.put(bucketName.toString(), getAllInfo(String.valueOf(bucketName)));
			bucketInfoList.add(bucketsMap);
		}
		return bucketInfoList;
	}
	
}