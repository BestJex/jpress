/**
 * Copyright (c) 2016-2019, Michael Yang 杨福海 (fuhai999@gmail.com).
 * <p>
 * Licensed under the GNU Lesser General Public License (LGPL) ,Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl-3.0.txt
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jpress.web.admin;

import com.jfinal.aop.Inject;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jboot.web.validate.EmptyValidate;
import io.jboot.web.validate.Form;
import io.jpress.JPressConsts;
import io.jpress.core.menu.annotation.AdminMenu;
import io.jpress.model.Coupon;
import io.jpress.service.CouponService;
import io.jpress.web.base.AdminControllerBase;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 首页
 * @Package io.jpress.web.admin
 */
@RequestMapping(value = "/admin/order/coupon", viewPath = JPressConsts.DEFAULT_ADMIN_VIEW)
public class _CouponController extends AdminControllerBase {

    private static final Log LOG = Log.getLog(_CouponController.class);

    @Inject
    private CouponService couponService;


    @AdminMenu(text = "优惠券", groupId = JPressConsts.SYSTEM_MENU_ORDER, order = 2)
    public void index() {
        Page<Coupon> page = couponService.paginate(getPagePara(),10);
        setAttr("page",page);
        render("order/coupon.html");
    }


    public void edit() {
        Coupon coupon = couponService.findById(getPara());
        setAttr("coupon",coupon);
        render("order/coupon_edit.html");
    }

    @EmptyValidate({
            @Form(name = "coupon.amount",message = "优惠券金额不能为空"),
            @Form(name = "coupon.quota",message = "优惠券发行数量不能为空"),
    })
    public void doSave(){
        Coupon coupon = getModel(Coupon.class);
        couponService.saveOrUpdate(coupon);
        renderOkJson();
    }


    public void takes(){
        render("order/coupon_takes.html");
    }

    public void useds(){
        render("order/coupon_useds.html");
    }



}