package Performance.Performance;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import Performance.Performance.*;



public class ViewHistory_LoopTest {
	private int Numbers = 10;
	private long ViewHist_LoadUp_Time_total = 0;
	private String Browser[] = {"Chrome","IE32"};
	//private String Browser[] = {"IE32"};
	

  @Test
  public void main() throws FindFailed, IOException{
	  Date date = new Date();
	  SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd_HHmmss");
	  ViewHistory viewhistory = new ViewHistory();
	  
	  FileWriter writer;
	  writer = new FileWriter("C:\\Performance_Result\\ViewHistory_Time_" + ft.format(date) + ".csv");
	  writer.append("Desc,No,Result,Browser" + "\n");
	  for (int x = 0; x < Browser.length ; x++){
		  System.out.println(Browser[x]);
		  ViewHist_LoadUp_Time_total = 0;
		  for (int i=1; i < (Numbers + 1); i++){
			  System.out.println("Loop" + i);
			  viewhistory.beforelooptest(Browser[x]);
			  viewhistory.main();
			  viewhistory.afterTest();
			  writer.append("View History Time" + ',' + i + ',' + viewhistory.History_LoadUp_Time +  ',' + Browser[x] + '\n');
			  ViewHist_LoadUp_Time_total += viewhistory.History_LoadUp_Time;
			  try {
				  Thread.sleep(1000);
			  } catch (InterruptedException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
	  
		  System.out.println("The average view history time is " + (ViewHist_LoadUp_Time_total / Numbers) );
	  
		  writer.append("View History Time" + ',' + "Average" + ',' + (ViewHist_LoadUp_Time_total / Numbers) +  ',' + Browser[x] + '\n');
	  }
	  writer.flush();
	  writer.close();  
  }
}
