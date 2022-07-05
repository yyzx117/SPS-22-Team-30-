import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageClass;
import com.google.cloud.storage.StorageOptions;

public class CreateBucketWithStorageClassAndLocation {
  public static void createBucketWithStorageClassAndLocation(String projectId, String bucketName) {
    // The ID of your GCP project
    projectId = "SPS-22-Team-30-";

    // The ID to give your GCS bucket
    bucketName = "asl_image_storage";

    Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();

    // See the StorageClass documentation for other valid storage classes:
    // https://googleapis.dev/java/google-cloud-clients/latest/com/google/cloud/storage/StorageClass.html
    StorageClass storageClass = StorageClass.COLDLINE;

    // See this documentation for other valid locations:
    // http://g.co/cloud/storage/docs/bucket-locations#location-mr
    String location = "US";

    Bucket bucket =
        storage.create(
            BucketInfo.newBuilder(bucketName)
                .setStorageClass(storageClass)
                .setLocation(location)
                .build());

    System.out.println(
        "Created bucket "
            + bucket.getName()
            + " in "
            + bucket.getLocation()
            + " with storage class "
            + bucket.getStorageClass());
  }
}