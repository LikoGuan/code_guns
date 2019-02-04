package com.stylefeng.guns.modular.foundation.service.impl;

import com.stylefeng.guns.modular.system.model.Brand;
import com.stylefeng.guns.modular.system.dao.BrandMapper;
import com.stylefeng.guns.modular.foundation.service.IBrandService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-22
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements IBrandService {

}
