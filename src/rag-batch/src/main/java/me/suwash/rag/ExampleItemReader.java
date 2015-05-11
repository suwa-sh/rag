package me.suwash.rag;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * {@link ItemReader} with hard-coded input data.
 */

@Component("reader")
@Scope("step")
public class ExampleItemReader implements ItemReader<ExampleInDto> {

    private String[] inputs = {"/path/to/boundary1", "/path/to/boudary2.sh arg1", "C:\\path\\to\\windows\\boudary3.bat arg1 arg2 arg3"};

    private int index = 0;

    @Value("#{jobParameters['index']}")
    public void setIndex(int index) {
        System.out.println("index:" + index + " から処理を開始します。");
        this.index = index;
    }

    private Date startDatetime;

    @Value("#{jobParameters['startDatetime']}")
    public void setStartDatetime(String startDatetime) {
        try {
            this.startDatetime = DateFormat.getDateInstance().parse(startDatetime);
        } catch (ParseException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
    }

    /**
     * Reads next record from input
     */
    public ExampleInDto read() throws Exception {
        // TODO 引数のタイムスタンプを利用して何かする
        System.out.println("◼◼◼ ︎" + SimpleDateFormat.getTimeInstance().format(startDatetime));

        if (index < inputs.length) {
            ExampleInDto dto = new ExampleInDto();
            dto.setValue1(inputs[index]);
            index++;
            return dto;
        } else {
            return null;
        }
    }

}
