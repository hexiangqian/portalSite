package zy.news.amain;


import maoko.common.file.FileIDUtil;
import maoko.common.log.IWriteLog;
import maoko.common.log.Log4j2Writer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * app启动完成后启动
 * @author fanpei
 */
public class AfterAppStartUp implements ApplicationListener<ContextRefreshedEvent> {
    private static final IWriteLog LOG = new Log4j2Writer(AfterAppStartUp.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try {
            startsdkclient();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }


    /**
     * 客户端初始化
     *
     * @throws Exception
     */
    private void startsdkclient() throws Exception {
        FileIDUtil.init(1);//服务编号初始化
    }

}
