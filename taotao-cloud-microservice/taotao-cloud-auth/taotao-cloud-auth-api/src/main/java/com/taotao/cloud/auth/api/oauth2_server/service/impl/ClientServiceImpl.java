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
//package com.taotao.cloud.oauth2.api.oauth2_server.service.impl;
//
//import com.taotao.cloud.auth.api.dto.ClientDTO;
//import com.taotao.cloud.auth.api.query.ClientPageQuery;
//import com.taotao.cloud.common.constant.RedisConstant;
//import com.taotao.cloud.common.constant.SecurityConstant;
//import com.taotao.cloud.common.exception.BusinessException;
//import com.taotao.cloud.oauth2.api.oauth2_server.entity.Client;
//import com.taotao.cloud.oauth2.api.oauth2_server.repository.ClientSuperRepository;
//import com.taotao.cloud.oauth2.api.oauth2_server.service.IClientService;
//import com.taotao.cloud.redis.repository.RedisRepository;
//import java.util.List;
//import java.util.Optional;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
///**
// * ClientServiceImpl
// *
// * @author shuigedeng
// * @since 2020/4/29 15:22
// * @version 2022.03
// */
//@Service
//public class ClientServiceImpl implements IClientService {
//
//    private final static String LOCK_KEY_CLIENT_ID = RedisConstant.LOCK_KEY_PREFIX + "clientId:";
//
//    @Autowired
//    private RedisRepository redisRepository;
//    @Autowired
//    private ClientSuperRepository clientRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean saveClient(ClientDTO clientDTO) {
//        String clientId = clientDTO.getClientId();
//
//        boolean flag = true;
//        try {
//            getByClientId(clientId);
//        } catch (Exception e) {
//            flag = false;
//        }
//
//        if (flag) {
//            throw new BusinessException("clientId已存在");
//        }
//
//        Client client = new Client();
//        BeanUtils.copyProperties(clientDTO, client);
//        client.setClientSecret(passwordEncoder.encode(client.getClientSecretStr()));
//
//        clientRepository.save(client);
//        return true;
//    }
//
//    @Override
//    public Page<Client> listClient(ClientPageQuery clientPageQuery) {
//        return clientRepository.getPage(clientPageQuery);
//    }
//
//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public Boolean delByClientId(String clientId) {
//        clientRepository.delByClientId(clientId);
//        redisRepository.del(clientRedisKey(clientId));
//        return true;
//    }
//
//    @Override
//    public Client getByClientId(String clientId) {
//        Optional<Client> optionalClient = clientRepository.getByClientId(clientId);
//        return optionalClient.orElseThrow(() -> new BusinessException("字典数据不存在"));
//    }
//
//    @Override
//    public List<Client> getAllClient() {
//        return clientRepository.findAll();
//    }
//
//    @Override
//    public Boolean updateClient(String clientId, ClientDTO clientDto) {
//        Client client = getByClientId(clientId);
//        BeanUtils.copyProperties(clientDto, client);
//        clientRepository.save(client);
//        return true;
//    }
//
//    private String clientRedisKey(String clientId) {
//        return SecurityConstant.CACHE_CLIENT_KEY + ":" + clientId;
//    }
//}
