import com.lakala.crypto.AESUtil;
import com.lakala.crypto.KeyUtil;
import io.ipfs.multibase.Multibase;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.interfaces.ECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.Hex;

import java.io.StringReader;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author salahgao
 * @date 2019/12/30
 */
public class CBDC {

    public static void main(String[] args) throws Exception {

        test5();
    }

    public static void test5() throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        String id = "10002";
        double value = 100.00;
        String owner = "z3SiwDt8RLurvEoRPqbL2ytd5wCKu";
        String issuer = "中国人民银行";
        String crypto = null;
        String sign = null;

        String s1 = new StringBuilder().append(id).append(value).append(owner).append(issuer).toString();
        System.out.println("s1=" + s1);

        //String secretKey = KeyUtil.getBase64AESKey();
        String secretKey = "MHz5zqYOvvxRt9mbpOBycw==";
        System.out.println("secretKey=" + secretKey);
        crypto = AESUtil.encrypt(secretKey, s1.getBytes("UTF-8"));
        System.out.println("crypto=" + crypto);

        String privatekeyStr = "-----BEGIN EC PRIVATE KEY-----\n" +
                "MHcCAQEEIMb/N4oBMiINpLv1KPiyQ70EGirpOwk/OZU8vG74qjpsoAoGCCqGSM49\n" +
                "AwEHoUQDQgAE9IKwCYBRJ7CYlvdfOmwvK6R3XyfCt4Wid7KW/JYU3CuftJ3srRZj\n" +
                "e1AX717QrO2n0hl6wcsUhd5HkxPldq9LIQ==\n" +
                "-----END EC PRIVATE KEY-----\n";
        PEMParser pemParser = new PEMParser(new StringReader(privatekeyStr));
        PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(pemKeyPair.getPrivateKeyInfo().getEncoded()));

        String s2 = new StringBuilder(s1).append(crypto).toString();
        Signature signature = Signature.getInstance("SHA256WITHECDSA");
        signature.initSign(privateKey);
        signature.update(s2.getBytes("UTF-8"));
        sign = Base64.toBase64String(signature.sign());
        System.out.println("s2=" + s2);
        System.out.println("sign=" + sign);


        String publicKeyStr = "-----BEGIN PUBLIC KEY-----\n" +
                "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE9IKwCYBRJ7CYlvdfOmwvK6R3XyfC\n" +
                "t4Wid7KW/JYU3CuftJ3srRZje1AX717QrO2n0hl6wcsUhd5HkxPldq9LIQ==\n" +
                "-----END PUBLIC KEY-----\n";
        pemParser = new PEMParser(new StringReader(publicKeyStr));
        SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) pemParser.readObject();
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));

        signature.initVerify(publicKey);
        signature.update(s2.getBytes("UTF-8"));
        boolean result = signature.verify(Base64.decode(sign));
        System.out.println("verify result=" + result);

    }

    public static void test4() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        String alice = "-----BEGIN PUBLIC KEY-----\n" +
                "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEbybVWdu52HRfp6kIt9y4wFpHqpC1\n" +
                "7ZU2C8Ztk38SeHPjb6VnZnHmGlzdWoZ0WEoeG2n8896PAwpkeLJHmqFq2A==\n" +
                "-----END PUBLIC KEY-----\n";
        String bob = "-----BEGIN PUBLIC KEY-----\n" +
                "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAE4W5p7fuL7ux9EYHX/0I/LyYmofgv\n" +
                "nD/1S1Kw+RVBxLjUmQc1loQVp1Rl1CCN1p+x+JhYlqAS6Dp7aEXqUjJd3Q==\n" +
                "-----END PUBLIC KEY-----\n";
        String str = alice;
        PEMParser pemParser = new PEMParser(new StringReader(str));
        SubjectPublicKeyInfo subjectPublicKeyInfo = (SubjectPublicKeyInfo) pemParser.readObject();
        KeyFactory keyFactory = KeyFactory.getInstance("EC", "BC");
        PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(subjectPublicKeyInfo.getEncoded()));
        ECPublicKey ecPublicKey = (ECPublicKey) publicKey;
        ECPoint ecPoint = ecPublicKey.getQ();
        byte[] input = ecPoint.getEncoded(false);
        System.out.println("toHexString:" + Hex.toHexString(input).toUpperCase());
        byte[] pubKeyHash = sha256hash160(input);
        String encoded = Multibase.encode(Multibase.Base.Base58BTC, pubKeyHash);
        System.out.println("toBase58:" + encoded);
    }

    public static void test3() throws Exception {

        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        keyPairGenerator.initialize(256);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        ECPublicKey ecPublicKey = (ECPublicKey) keyPair.getPublic();
        ECPoint ecPoint = ecPublicKey.getQ();
        byte[] input = ecPoint.getEncoded(true); //是否压缩
        System.out.println("toHexString:" + Hex.toHexString(ecPoint.getRawXCoord().getEncoded()).toUpperCase());
        System.out.println("toHexString:" + Hex.toHexString(ecPoint.getRawYCoord().getEncoded()).toUpperCase());
        System.out.println("toHexString:" + Hex.toHexString(input).toUpperCase());
        byte[] pubKeyHash = sha256hash160(input);
        String encoded = Multibase.encode(Multibase.Base.Base58BTC, pubKeyHash);
        System.out.println("toBase58:" + encoded);

    }

    public static void test2() throws Exception {

        Security.addProvider(new BouncyCastleProvider());

        // The parameters of the secp256k1 curve that Bitcoin uses.
        X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");

        //The parameters of the secp256k1 curve that Bitcoin uses.
        ECDomainParameters CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());

        SecureRandom secureRandom = new SecureRandom();

        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keygenParams = new ECKeyGenerationParameters(CURVE, secureRandom);
        generator.init(keygenParams);
        AsymmetricCipherKeyPair keypair = generator.generateKeyPair();

        ECPrivateKeyParameters privParams = (ECPrivateKeyParameters) keypair.getPrivate();
        ECPublicKeyParameters pubParams = (ECPublicKeyParameters) keypair.getPublic();

        ECPoint ecPoint = pubParams.getQ();
        byte[] input = ecPoint.getEncoded(false);
        byte[] pubKeyHash = sha256hash160(input);

        String encoded = Multibase.encode(Multibase.Base.Base58BTC, pubKeyHash);
        System.out.println("Base58:" + encoded);
        byte[] decoded = Multibase.decode(encoded);

    }

    public static byte[] sha256hash160(byte[] input) throws NoSuchAlgorithmException {
        //byte[] sha256 = Sha256Hash.hash(input);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(input, 0, input.length);
        byte[] sha256 = messageDigest.digest();

        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(sha256, 0, sha256.length);
        byte[] out = new byte[20];
        digest.doFinal(out, 0);
        return out;
    }

    public static void test1() throws Exception {
        KeyPair keyPair = getKeyPair();
        System.out.println("Format:" + keyPair.getPrivate().getFormat());
        System.out.println("Format:" + keyPair.getPublic().getFormat());
        System.out.println("Format:" + keyPair.getPublic().getAlgorithm());
        String privateKey = Base64.toBase64String(keyPair.getPrivate().getEncoded());
        String publicKey = Base64.toBase64String(keyPair.getPublic().getEncoded());
        System.out.println("privateKey:" + privateKey);
        System.out.println("publicKey:" + publicKey);

//        byte[] bytes1 = {1};
//        // 两次哈希PublicKeySpec
//        byte[] bytes2 = DigestUtils.sha256(DigestUtils.sha256(bytes1));
//        String encoded = Multibase.encode(Multibase.Base.Base58BTC, bytes2);
//        byte[] decoded = Multibase.decode(encoded);
    }

    public static KeyPair getKeyPair() throws Exception {
        Security.addProvider(new BouncyCastleProvider());
        KeyPairGenerator kpGen = KeyPairGenerator.getInstance("ECDSA", "BC");
        kpGen.initialize(256);
        return kpGen.generateKeyPair();
    }
}
