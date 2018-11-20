package cn.shovi.edu.service;

import cn.shovi.edu.common.bean.Tbuser;
import cn.shovi.edu.common.bean.TbuserExample;
import cn.shovi.edu.common.jedis.JedisClient;
import cn.shovi.edu.common.utils.EduResult;
import cn.shovi.edu.common.utils.JsonUtils;
import cn.shovi.edu.dao.mapper.TbuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.security.auth.login.CredentialException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private TbuserMapper mapper;
    @Autowired
    private JedisClient jedisClient;
//    @Value("${SESSION_EXPIRE}")
//    private Integer SESSION_EXPIRE;

    public EduResult register(Tbuser user) {
        //数据有效性校验
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPasswd())) {
            return EduResult.build(400, "用户数据不完整，注册失败");
        }
        //1：用户名 2：手机号 3：邮箱
        EduResult result = checkData(user.getUsername(), 1);
        if (result.getStatus()==521) {
            return EduResult.build(400, "此手机号码已经被注册");
        }else {
//        result = checkData(user.getPhone(), 2);
//        if (!(boolean)result.getData()) {
//            return EduResult.build(400, "手机号已经被占用");
//        }
            //补全pojo的属性
            user.setUseruuid(UUID.randomUUID().toString());
            user.setUserveritype("native");
            user.setUsernickname(user.getUsername() + "");
            user.setUserregtime(new Date());
            //对密码进行md5加密
            String md5Pass = DigestUtils.md5DigestAsHex((user.getPasswd() + user.getUserveritype()).getBytes());
            user.setPasswd(md5Pass);
            //把用户数据插入到数据库中
            mapper.insert(user);
            user.setPasswd(null);
            //返回添加成功
            return EduResult.build(200, "注册成功", user);
        }
    }

    public EduResult login(String username, String passwd) {

        TbuserExample example = new TbuserExample();
        TbuserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Tbuser> list = mapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            //1.返回登录失败
            return EduResult.build(400, "用户名或密码错误");
        }
        //2.取用户信息
        Tbuser tbuser = list.get(0);
        if(!(tbuser.getPasswd().equals(DigestUtils.md5DigestAsHex((passwd+tbuser.getUserveritype()).getBytes()))))
        {
           return EduResult.build(400, "用户名或密码错误");
        }
//        // 3、如果正确生成token。
//        String token = UUID.randomUUID().toString();
//        // 4、把用户信息写入redis，key：token value：用户信息
        tbuser.setPasswd(null);
//        jedisClient.set("SESSION:" + token, JsonUtils.objectToJson(tbuser));
//        // 5、设置Session的过期时间
//        jedisClient.expire("SESSION:" + token, 1800);
//        // 6、把token返回
//        return EduResult.ok(token);
        return EduResult.ok(tbuser);
    }

    public EduResult checkData(String param, int type) {
        //根据不同的type生成不同的查询条件
        TbuserExample example = new TbuserExample();
        TbuserExample.Criteria criteria = example.createCriteria();
        //1：用户名 2：手机号 3：邮箱
        if (type == 1) {
            criteria.andUsernameEqualTo(param);
        }
//        else if (type == 2) {
//            criteria.andPhoneEqualTo(param);
//        } else if (type == 3) {
//            criteria.andEmailEqualTo(param);
//        } else {
//            return EduResult.build(400, "数据类型错误");
//        }
        //执行查询
        List<Tbuser> list = mapper.selectByExample(example);
        //判断结果中是否包含数据
        if (list != null && list.size()>0) {
            //如果有数据
            return EduResult.build(521,"该手机号码已注册");
        }
        //如果有数据返回
        return EduResult.build(522,"该手机号码可以使用");
    }
}
