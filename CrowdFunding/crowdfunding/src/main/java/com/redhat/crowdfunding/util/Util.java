package com.redhat.crowdfunding.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import com.redhat.crowdfunding.contract.CrowdFundingContract;

/**
 * @author littleredhat
 */
public class Util {

	/**
	 * 获取管理员凭证
	 * 
	 * @param contractAddress
	 *            合约地址
	 * @return
	 * @throws Exception
	 */
	public static Credentials GetCredentials() {
		// 管理员凭证
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
		return credentials;
	}

	/**
	 * 获取发送者凭证
	 * 
	 * @param password
	 *            发送者钱包密码
	 * @param content
	 *            发送者钱包内容
	 * @param contractAddress
	 *            合约地址
	 * @return
	 * @throws Exception
	 */
	public static Credentials GetCredentials(String password, String content) {
		// 临时文件
		File tmp = null;
		try {
			tmp = File.createTempFile("key", ".tmp");
			// 自动删除
			tmp.deleteOnExit();
			// 写入内容
			BufferedWriter out = new BufferedWriter(new FileWriter(tmp));
			out.write(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 发送者凭证
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(password, tmp);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
		return credentials;
	}

	/**
	 * 返回众筹合约
	 * 
	 * @param credentials
	 *            钱包凭证
	 * @param contractAddress
	 *            合约地址
	 * @return
	 * @throws Exception
	 */
	public static CrowdFundingContract GetCrowdFundingContract(Credentials credentials, String contractAddress) {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());
		// 获取合约
		CrowdFundingContract contract = new CrowdFundingContract(contractAddress, web3j, credentials,
				BigInteger.valueOf(Consts.GAS_PRICE), BigInteger.valueOf(Consts.GAS_LIMIT));
		return contract;
	}
}
