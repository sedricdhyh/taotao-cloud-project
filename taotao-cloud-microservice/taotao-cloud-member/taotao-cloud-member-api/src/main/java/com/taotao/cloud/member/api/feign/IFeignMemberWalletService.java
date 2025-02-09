package com.taotao.cloud.member.api.feign;

import com.taotao.cloud.common.constant.ServiceName;
import com.taotao.cloud.member.api.dto.MemberWalletUpdateDTO;
import com.taotao.cloud.member.api.feign.fallback.FeignMemberServiceFallback;
import com.taotao.cloud.member.api.vo.MemberRechargeVO;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = ServiceName.TAOTAO_CLOUD_MEMBER_CENTER, fallbackFactory = FeignMemberServiceFallback.class)
public interface IFeignMemberWalletService {
	void increase(MemberWalletUpdateDTO memberWalletUpdateDTO);

	void save(Long id, String username);
}
