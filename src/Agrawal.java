import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Agrawal {
	
	public static void main(String [] args) throws IOException{
		
		if(args.length != 3){
			System.out.println("Use format Agrawal <input path> <output path> <minimum support>");
			System.exit(1);
		}
		Configuration conf = new Configuration();
		conf.set("minSupport", args[2]);
		
		Job job = new Job(conf);
		job.setJarByClass(Agrawal.class);
		job.setJobName("Agrawal Process");
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		job.setMapperClass(PatternMapper.class);
		job.setReducerClass(PatternReducer.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
	}
	
}
