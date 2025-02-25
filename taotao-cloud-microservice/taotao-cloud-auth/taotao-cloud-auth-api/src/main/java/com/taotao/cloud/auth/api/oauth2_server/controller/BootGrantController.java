///*
// * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.taotao.cloud.oauth2.api.oauth2_server.controller;
//
//import java.util.Map;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.security.oauth2.provider.AuthorizationRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.servlet.ModelAndView;
//import springfox.documentation.annotations.ApiIgnore;
//
///**
// * 授权控制器<br>
// *
// * @author shuigedeng
// * @since 2020/7/24 17:05
// * @version 2022.03
// */
//@ApiIgnore
//@Controller
//@SessionAttributes("authorizationRequest")
//public class BootGrantController {
//
//    @RequestMapping("/custom/confirm_access")
//    public ModelAndView getAccessConfirmation(Map<String, Object> model, HttpServletRequest request) throws Exception {
//        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
//        ModelAndView view = new ModelAndView();
//        view.setViewName("base-grant");
//
//        view.addObject("clientId", authorizationRequest.getClientId());
//        view.addObject("scopes", authorizationRequest.getScope());
//        return view;
//    }
//}
