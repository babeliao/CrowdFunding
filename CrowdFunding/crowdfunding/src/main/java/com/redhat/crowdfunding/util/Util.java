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
	 * ��ȡ����Աƾ֤
	 * 
	 * @param contractAddress
	 *            ��Լ��ַ
	 * @return
	 * @throws Exception
	 */
	public static Credentials GetCredentials() {
		// ����Աƾ֤
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
		return credentials;
	}

	/**
	 * ��ȡ������ƾ֤
	 * 
	 * @param password
	 *            ������Ǯ������
	 * @param content
	 *            ������Ǯ������
	 * @param contractAddress
	 *            ��Լ��ַ
	 * @return
	 * @throws Exception
	 */
	public static Credentials GetCredentials(String password, String content) {
		// ��ʱ�ļ�
		File tmp = null;
		try {
			tmp = File.createTempFile("key", ".tmp");
			// �Զ�ɾ��
			tmp.deleteOnExit();
			// д������
			BufferedWriter out = new BufferedWriter(new FileWriter(tmp));
			out.write(content);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// ������ƾ֤
		Credentials credentials = null;
		try {
			credentials = WalletUtils.loadCredentials(password, tmp);
		} catch (IOException | CipherException e) {
			e.printStackTrace();
		}
		return credentials;
	}

	/**
	 * �����ڳ��Լ
	 * 
	 * @param credentials
	 *            Ǯ��ƾ֤
	 * @param contractAddress
	 *            ��Լ��ַ
	 * @return
	 * @throws Exception
	 */
	public static CrowdFundingContract GetCrowdFundingContract(Credentials credentials, String contractAddress) {
		// defaults to http://localhost:8545/
		Web3j web3j = Web3j.build(new HttpService());
		// ��ȡ��Լ
		CrowdFundingContract contract = new CrowdFundingContract(contractAddress, web3j, credentials,
				BigInteger.valueOf(Consts.GAS_PRICE), BigInteger.valueOf(Consts.GAS_LIMIT));
		return contract;
	}
}