package org.danmo.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.danmo.cache.LocalCache;
import org.danmo.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class BaseEntityMetaObjectHandler implements MetaObjectHandler {
    @Autowired
    HttpServletRequest request;
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充[insert]...");
        log.info(metaObject.toString());

        metaObject.setValue("createTime", new Date(System.currentTimeMillis()));
        metaObject.setValue("updateTime", new Date(System.currentTimeMillis()));
        String token = request.getHeader("token");
        User user = (User) LocalCache.get(token);
        metaObject.setValue("createUser", user.getUsername());
        metaObject.setValue("updateUser", user.getUsername());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充[update]...");
        log.info(metaObject.toString());

        metaObject.setValue("updateTime", new Date(System.currentTimeMillis()));
        String token = request.getHeader("token");
        User user = (User) LocalCache.get(token);
        metaObject.setValue("updateUser", user.getUsername());
    }
}
