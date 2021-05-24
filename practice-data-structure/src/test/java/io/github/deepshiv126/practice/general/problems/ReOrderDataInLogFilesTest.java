package io.github.deepshiv126.practice.general.problems;

import io.github.deepshiv126.practice.general.problems.ReOrderDataInLogFiles;
import org.testng.annotations.Test;

public class ReOrderDataInLogFilesTest {

    @Test
    public void reorderLogData() {
   //     String[] logs = {"log1 aaa","log2 bbb","log4 ccc","log3 ccc","log6 eee","log5 ddd","log8 999","log7 123"};
        String[] logs = {"log5 ddd","log8 999","log7 123"};

        ReOrderDataInLogFiles reOrderDataInLogFiles  = new ReOrderDataInLogFiles();
        String[] output = reOrderDataInLogFiles.reOrderLogData(logs);
        for(String o : output){
           // System.out.println(o);
        }
    }

    @Test
    public void understanding() {
        String[] logs = {"a","b","c","c","e","d","9","1"};
        ReOrderDataInLogFiles reOrderDataInLogFiles  = new ReOrderDataInLogFiles();
        String[] output = reOrderDataInLogFiles.understanding(logs);

        for(String o : output){
          //  System.out.println(o);
        }
    }


}
