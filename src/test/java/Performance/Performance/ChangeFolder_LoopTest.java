package Performance.Performance;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import Performance.Performance.*;



public class ChangeFolder_LoopTest {
	private int Numbers = 10;
	private long ChangeFolder_Time_total = 0;
	//private String Browser[] = {"Android","Chrome","IE32"};
	private String Browser[] = {"Chrome","IE32"};
	//private String Browser[] = {"IE32"};
	

  @Test
  public void main() throws FindFailed, IOException{
	  Date date = new Date();
	  SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd_HHmmss");
	  ChangeFolder changefolder = new ChangeFolder();
	  
	  FileWriter writer;
	  writer = new FileWriter("C:\\Performance_Result\\ChangeFolder_Time_" + ft.format(date) + ".csv");
	  writer.append("Desc,No,Result,Browser" + "\n");
	  for (int x = 0; x < Browser.length ; x++){
		  System.out.println(Browser[x]);
		  ChangeFolder_Time_total = 0;
		  
		  for (int i=1; i < (Numbers + 1); i++){
			  System.out.println("Loop" + i);
			  changefolder.beforelooptest(Browser[x]);
			  changefolder.main();
			  changefolder.afterTest();
			  writer.append("Change Folder Time" + ',' + i + ',' + changefolder.ChangeFolder_Time + ',' + Browser[x] + '\n');
			  ChangeFolder_Time_total += changefolder.ChangeFolder_Time;
			  try {
				  Thread.sleep(4000);
			  } catch (InterruptedException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
		  System.out.println("The average Change Folder time is " + (ChangeFolder_Time_total / Numbers) );
		  
		  writer.append("Change Folder Time" + ',' + "Average" + ',' + (ChangeFolder_Time_total / Numbers) + ',' + Browser[x] + '\n');
	  }
      
	  
	  writer.flush();
	  writer.close();  
  }
}
