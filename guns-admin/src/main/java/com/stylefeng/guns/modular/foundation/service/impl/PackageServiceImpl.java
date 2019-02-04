package com.stylefeng.guns.modular.foundation.service.impl;

import com.stylefeng.guns.modular.system.model.Package;
import com.stylefeng.guns.modular.system.dao.PackageMapper;
import com.stylefeng.guns.modular.foundation.service.IPackageService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 包装表 服务实现类
 * </p>
 *
 * @author stylefeng
 * @since 2018-10-25
 */
@Service
public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package> implements IPackageService {

}
