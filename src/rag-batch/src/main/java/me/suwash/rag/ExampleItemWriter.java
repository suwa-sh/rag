package me.suwash.rag;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * Dummy {@link ItemWriter} which only logs data it receives.
 */
@Component("writer")
@Scope("step")
public class ExampleItemWriter implements ItemWriter<ExampleOutDto> {

    private static final Log log = LogFactory.getLog(ExampleItemWriter.class);

    /**
     * @see ItemWriter#write(java.util.List)
     */
    public void write(List<? extends ExampleOutDto> data) throws Exception {
        log.info(data);
    }

}
