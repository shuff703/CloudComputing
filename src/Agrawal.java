/**Apriori Algorithm on HDFS
 * 
 * @Author: Scott Huff     
 * 
 * */

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Agrawal {
	//Driver Class
	public static void main(String [] args) 
			throws IOException, ClassNotFoundException, InterruptedException{
		
		if(args.length != 3){
			System.out.println("Use format Agrawal <input path> <output path> <minimum support>");
			System.exit(1);
		}
		
		//Support argument
		Configuration conf = new Configuration();
		conf.set("minSupport", args[2]);
		
		@SuppressWarnings("deprecation")
		Job job = new Job(conf);
		job.setJarByClass(Agrawal.class);
		job.setJobName("Subsets and support");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path("intermediate.txt"));
		
		job.setMapperClass(PatternMapper.class);
		job.setReducerClass(PatternReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		Job job2 = new Job();
		job2.setJarByClass(Agrawal.class);
		job2.setJobName("Confidence Computation");
		
		System.exit(job.waitForCompletion(true) ? 0:1);
		
	}
	
}
