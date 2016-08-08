package rml.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AdminLoginController {
	@RequestMapping
	public String login(HttpServletRequest request){
		System.out.println("------------login");
		return "admin/login";
	}
	
	@RequestMapping("/login")
	public String loginAgain(HttpServletRequest request){
		System.out.println("-----------loginAgain");
		UsernamePasswordToken token = new UsernamePasswordToken(
				request.getParameter("username"), request.getParameter("password"));
		System.out.println("----------username-----" + token.getUsername());
		System.out.println("----------password-----" + token.getPassword().toString());
		//发出登陆请求  
        SecurityUtils.getSubject().login(token);
		return "admin/login";
	}
	
	@RequestMapping("/success")
	public String loginSuccess(HttpServletRequest request){
		String resultPageURL = "admin/login";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordToken token = new UsernamePasswordToken(
				request.getParameter("username"), request.getParameter("password"));
		/*//发出登陆请求  
        SecurityUtils.getSubject().login(token);*/
        
      //获取当前的Subject  
        Subject currentUser = SecurityUtils.getSubject();  
        try {  
            //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查  
            //每个Realm都能在必要时对提交的AuthenticationTokens作出反应  
            //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法  
            System.out.println("对用户[" + username + "]进行登录验证..验证开始");  
            if(username.equals("")||username==null){
    			throw new UnknownAccountException("UnknownAccountException");
    		}
            if(password.equals("")||password==null){
    			throw new IncorrectCredentialsException("IncorrectCredentialsException");
    		}
            currentUser.login(token);  
            System.out.println("对用户[" + username + "]进行登录验证..验证通过");  
            resultPageURL = "redirect:/login/index.do";  
        }catch(UnknownAccountException uae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");  
            request.setAttribute("message_login", "未知账户");  
        }catch(IncorrectCredentialsException ice){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");  
            request.setAttribute("message_login", "密码不正确");  
        }catch(LockedAccountException lae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");  
            request.setAttribute("message_login", "账户已锁定");  
        }catch(ExcessiveAttemptsException eae){  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");  
            request.setAttribute("message_login", "用户名或密码错误次数过多");  
        }catch(AuthenticationException ae){  
            //通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景  
            System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");  
            ae.printStackTrace();  
            request.setAttribute("message_login", "用户名或密码不正确");  
        }  
        
		return resultPageURL;
	}
	
//	@RequiresPermissions("admin:manage")
	@RequestMapping("/index")
	public String loginIndex(HttpServletRequest request){
		System.out.println("---------------permission---------");
		return "admin/index";
	}

}
