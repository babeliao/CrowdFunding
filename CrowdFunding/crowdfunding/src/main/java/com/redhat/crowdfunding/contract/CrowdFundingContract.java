package com.redhat.crowdfunding.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.exceptions.TransactionTimeoutException;
import org.web3j.tx.Contract;

import com.redhat.crowdfunding.util.Consts;

/**
 * @author littleredhat
 */
public class CrowdFundingContract extends Contract implements CrowdFundingInterface {

	/**
	 * 众筹合约
	 * 
	 * @param contractAddress
	 *            合约地址
	 * @param web3j
	 *            JSON-RPC请求
	 * @param credentials
	 *            发起者凭证
	 * @param gasPrice
	 *            gas价格
	 * @param gasLimit
	 *            gas上限
	 */
	public CrowdFundingContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit) {
		super(contractAddress, web3j, credentials, gasPrice, gasLimit);
	}

	/**
	 * 部署合约
	 * 
	 * @param web3j
	 *            JSON-RPC请求
	 * @param credentials
	 *            发起者凭证
	 * @param gasPrice
	 *            gas价格
	 * @param gasLimit
	 *            gas上限
	 * @param initialValue
	 *            初始金币
	 * @return
	 */
	public static Future<CrowdFundingContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice,
			BigInteger gasLimit, BigInteger initialValue, Address beneficiary) {
		String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(beneficiary));
		return deployAsync(CrowdFundingContract.class, web3j, credentials, gasPrice, gasLimit, Consts.BINARY,
				encodedConstructor, initialValue);
	}

	public TransactionReceipt sendCoin(BigInteger value) {
		Function function = new Function("sendCoin", Arrays.asList(), Arrays.<TypeReference<?>>asList());
		try {
			return executeTransaction(FunctionEncoder.encode(function), value);
		} catch (ExecutionException | InterruptedException | TransactionTimeoutException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Future<TransactionReceipt> endCrowd() {
		Function function = new Function("endCrowd", Arrays.asList(), Arrays.<TypeReference<?>>asList());
		return executeTransactionAsync(function);
	}

	// 暂时不用
	public EventValues processCrowdEndEvent(TransactionReceipt future) {
		/*
		 * String name 函数名字
		 * List<TypeReference<?>> indexedParameters 含索引的参数
		 * List<TypeReference<?>> nonIndexedParameters 不含索引的参数
		 */
		Event event = new Event("CrowdEnd", Arrays.<TypeReference<?>>asList(),
				Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
		return extractEventParameters(event, future).get(0);
	}
}
