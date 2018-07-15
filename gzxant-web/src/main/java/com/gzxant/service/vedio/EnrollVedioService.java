package com.gzxant.service.vedio;

import com.gzxant.base.service.impl.BaseService;
import com.gzxant.dao.enroll.vedio.EnrollVedioDao;
import com.gzxant.entity.enroll.vedio.EnrollVedio;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class EnrollVedioService extends BaseService<EnrollVedioDao,EnrollVedio> implements IEnrollVedioService {

}
