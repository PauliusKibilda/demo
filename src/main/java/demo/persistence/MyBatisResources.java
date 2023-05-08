//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package demo.persistence;

import java.io.IOException;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.cdi.SessionFactoryProvider;

@ApplicationScoped
public class MyBatisResources {
    public MyBatisResources() {
    }

    @Produces
    @ApplicationScoped
    @SessionFactoryProvider
    private SqlSessionFactory produceSqlSessionFactory() {
        try {
            return (new SqlSessionFactoryBuilder()).build(Resources.getResourceAsStream("MyBatisConfig.xml"));
        } catch (IOException var2) {
            throw new RuntimeException("MyBatisResources.produceSqlSessionFactory(): ", var2);
        }
    }
}
