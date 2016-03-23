package Performance.Performance;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.sikuli.script.FindFailed;
import org.testng.annotations.Test;

import Performance.Performance.*;



public class FolderSelect2DocList_LoopTest {
	private int Numbers = 10;
	private long DocList_LoadUp_Time_total = 0;
	private String Browser[] = {"Chrome","IE32"};
	//private String Browser[] = {"IE32"};

  @Test
  public void main() throws FindFailed, IOException{
	  Date date = new Date();
	  SimpleDateFormat ft = new SimpleDateFormat ("yyyyMMdd_HHmmss");
	  FolderSelect2DocList folder_doclist = new FolderSelect2DocList();
	  
	  FileWriter writer;
	  writer = new FileWriter("C:\\Performance_Result\\FolderSelect2DocList_Time_" + ft.format(date) + ".csv");
	  writer.append("Desc,No,Result,Browser" + "\n");
	  for (int x = 0; x < Browser.length ; x++){
		  System.out.println(Browser[x]);
		  DocList_LoadUp_Time_total = 0;
		  
		  for (int i=1; i < (Numbers + 1); i++){
			  System.out.println("Loop" + i);
			  folder_doclist.beforelooptest(Browser[x]);
			  folder_doclist.main();
			  folder_doclist.afterTest();
			  writer.append("Display Document List Time" + ',' + i + ',' + folder_doclist.DocList_LoadUp_Time + ',' + Browser[x] + '\n');
			  DocList_LoadUp_Time_total += folder_doclist.DocList_LoadUp_Time;
			  try {
				  Thread.sleep(1000);
			  } catch (InterruptedException e) {
				  // TODO Auto-generated catch block
				  e.printStackTrace();
			  }
		  }
	  
		  System.out.println("The average display document list time is " + (DocList_LoadUp_Time_total / Numbers) );
	  
		  writer.append("Display Document List Time" + ',' + "Average" + ',' + (DocList_LoadUp_Time_total / Numbers) + ',' + Browser[x] + '\n');
	  }
	  writer.flush();
	  writer.close();  
  }
}
