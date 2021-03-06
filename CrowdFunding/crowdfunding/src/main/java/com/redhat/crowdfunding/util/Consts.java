package com.redhat.crowdfunding.util;

import java.math.BigInteger;

/**
 * @author littleredhat
 */
public class Consts {
	// 钱包密码
	public static String PASSWORD = "123456";
	// 钱包路径
	public static String PATH = "F:\\chain\\geth-win64\\chain\\keystore\\UTC--2017-05-03T17-48-46.721084800Z--6c97ea3f4f71669412aab8b7f705e253ce14064c";
	// 合约二进制
	public static String BINARY = "6060604052341561000c57fe5b60405160208061021b833981016040528080519060200190919050505b80600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b505b61019f8061007c6000396000f30060606040526000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680638305eaa314610046578063c621fe9714610058575bfe5b341561004e57fe5b610056610062565b005b610060610160565b005b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc6001549081150290604051809050600060405180830381858888f1935050505015156100c657fe5b7f872b0148b7fe539f80bf003dc7f7e0138abefdeec118d9773b68ca17385fbebc600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff16600154604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390a160006001819055505b565b346001600082825401925050819055505b5600a165627a7a7230582019dead0d2078cdd0f8706d9521e8393aba03df89f31a8d3d0256d34bf57ea7350029";
	// Gas价格
	public static int GAS_PRICE = 1200000;
	// Gas上限
	public static int GAS_LIMIT = 2000000;
	// ETHER以太币
	public static BigInteger ETHER = new BigInteger("1000000000000000000");
	// 临时文件前缀
	public static String PREFIX = "key";
	// 临时文件后缀
	public static String SUFFIX = ".tmp";
}
