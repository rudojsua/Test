
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableResult;

import java.util.Arrays;
import java.util.UUID;

public class ConnectBQ {
	
	public static void main(String[] args) throws Exception {	  
		//  BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
		  String path = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
		  System.out.println("GOOGLE_APPLICATION_CREDENTIALS = " + path);
		  
		  
		  
		  GoogleCredentials credentials =
				    ServiceAccountCredentials.getApplicationDefault()
				        .createScoped(Arrays.asList("https://www.googleapis.com/auth/drive",
                                "https://www.googleapis.com/auth/bigquery",
                                "https://www.googleapis.com/auth/cloud-platform"));

			// Initialize client that will be used to send requests. This client only needs to be created
			// once, and can be reused for multiple requests.
			BigQuery bigquery =
			    BigQueryOptions.newBuilder().setCredentials(credentials).build().getService();
				
				
				
		  //Dev
		  QueryJobConfiguration queryConfig =
			    QueryJobConfiguration.newBuilder(
			            "SELECT *  FROM `lilac_db_data_mart.lilac_batch_analysis` WHERE product_family_name = 'GANTENERUMAB'")
			        // Use standard SQL syntax for queries.
			        // See: https://cloud.google.com/bigquery/sql-reference/
			        .setUseLegacySql(false)
			        .build(); 

			// Create a job ID so that we can safely retry.
			JobId jobId = JobId.of(UUID.randomUUID().toString());
			Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

			// Wait for the query to complete.
			queryJob = queryJob.waitFor();

			// Check for errors
			if (queryJob == null) {
			  throw new RuntimeException("Job no longer exists");
			} else if (queryJob.getStatus().getError() != null) {
			  // You can also look at queryJob.getStatus().getExecutionErrors() for all
			  // errors, not just the latest one.
			  throw new RuntimeException(queryJob.getStatus().getError().toString());
			}
			
			// Get the results.
			
			TableResult result = queryJob.getQueryResults();

			for (FieldValueList row : result.iterateAll()) {
			  String pfn  = row.get("product_family_name").getStringValue();
			  String ro = row.get("roche_number").getStringValue();
			  System.out.printf(
			      "PFN name: %s RO: %s\n", pfn, ro);
			}
			
	  }


}
