/**Apriori Algorithm on HDFS
 * 
 * @Author: Scott Huff     
 * 
 * */

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
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
		
		Configuration conf = new Configuration();
		conf.set("minSupport", args[2]);
		conf.set("itemSets", ",");
		conf.setDouble("totalTransactions", 0);

		Job job = new Job(conf);
		job.setJarByClass(Agrawal.class);
		job.setJobName("Subsets and support");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(PatternMapper.class);
		job.setCombinerClass(PatternReducer.class);
		job.setReducerClass(AssociationReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(DoubleWritable.class);
		
			
		System.exit(job.waitForCompletion(true) ? 0:1);
		
	}
	
}
