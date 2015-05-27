package me.suwash.rag;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * TODO クラスの説明。
 */
@Component("writer")
@Scope("step")
public class ExampleItemWriter implements ItemWriter<ExampleOutDto> {

    private static final Log log = LogFactory.getLog(ExampleItemWriter.class);

    /**
     * TODO メソッドの説明。
     *
     * @param data xxx
     * @throws Exception xxx
     */
    public void write(List<? extends ExampleOutDto> data) throws Exception {
        log.info(data);
    }

}
