package demo.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import demo.mybatis.dao.StoreMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@ApplicationScoped
public class CdiProducer {

    @Inject
    private SqlSessionFactory sqlSessionFactory;

    @Produces
    @ApplicationScoped
    public StoreMapper produceStoreMapper() {
        return sqlSessionFactory.openSession(true).getMapper(StoreMapper.class);
    }
}