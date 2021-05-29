package com.wq.leetcodeDK;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class mst1707yemz {
    public static Map <String, String> fmap = new HashMap<>();
    public static String find(String s){
        String tmp = s;
        String f = fmap.getOrDefault(tmp, tmp);
        while(!f.equals(tmp)){
            tmp = f;
            f = fmap.getOrDefault(tmp, tmp);
        }
        fmap.put(s, f);
        return f;
    }
    public static void main(String[] args) {
        String[] names = {"Fcclu(70)","Ommjh(63)","Dnsay(60)","Qbmk(45)","Unsb(26)","Gauuk(75)","Wzyyim(34)","Bnea(55)","Kri(71)","Qnaakk(76)","Gnplfi(68)","Hfp(97)","Qoi(70)","Ijveol(46)","Iidh(64)","Qiy(26)","Mcnef(59)","Hvueqc(91)","Obcbxb(54)","Dhe(79)","Jfq(26)","Uwjsu(41)","Wfmspz(39)","Ebov(96)","Ofl(72)","Uvkdpn(71)","Avcp(41)","Msyr(9)","Pgfpma(95)","Vbp(89)","Koaak(53)","Qyqifg(85)","Dwayf(97)","Oltadg(95)","Mwwvj(70)","Uxf(74)","Qvjp(6)","Grqrg(81)","Naf(3)","Xjjol(62)","Ibink(32)","Qxabri(41)","Ucqh(51)","Mtz(72)","Aeax(82)","Kxutz(5)","Qweye(15)","Ard(82)","Chycnm(4)","Hcvcgc(97)","Knpuq(61)","Yeekgc(11)","Ntfr(70)","Lucf(62)","Uhsg(23)","Csh(39)","Txixz(87)","Kgabb(80)","Weusps(79)","Nuq(61)","Drzsnw(87)","Xxmsn(98)","Onnev(77)","Owh(64)","Fpaf(46)","Hvia(6)","Kufa(95)","Chhmx(66)","Avmzs(39)","Okwuq(96)","Hrschk(30)","Ffwni(67)","Wpagta(25)","Npilye(14)","Axwtno(57)","Qxkjt(31)","Dwifi(51)","Kasgmw(95)","Vgxj(11)","Nsgbth(26)","Nzaz(51)","Owk(87)","Yjc(94)","Hljt(21)","Jvqg(47)","Alrksy(69)","Tlv(95)","Acohsf(86)","Qejo(60)","Gbclj(20)","Nekuam(17)","Meutux(64)","Tuvzkd(85)","Fvkhz(98)","Rngl(12)","Gbkq(77)","Uzgx(65)","Ghc(15)","Qsc(48)","Siv(47)"};
        String[] synonyms = {"(Gnplfi,Qxabri)","(Uzgx,Siv)","(Bnea,Lucf)","(Qnaakk,Msyr)","(Grqrg,Gbclj)","(Uhsg,Qejo)","(Csh,Wpagta)","(Xjjol,Lucf)","(Qoi,Obcbxb)","(Npilye,Vgxj)","(Aeax,Ghc)","(Txixz,Ffwni)","(Qweye,Qsc)","(Kri,Tuvzkd)","(Ommjh,Vbp)","(Pgfpma,Xxmsn)","(Uhsg,Csh)","(Qvjp,Kxutz)","(Qxkjt,Tlv)","(Wfmspz,Owk)","(Dwayf,Chycnm)","(Iidh,Qvjp)","(Dnsay,Rngl)","(Qweye,Tlv)","(Wzyyim,Kxutz)","(Hvueqc,Qejo)","(Tlv,Ghc)","(Hvia,Fvkhz)","(Msyr,Owk)","(Hrschk,Hljt)","(Owh,Gbclj)","(Dwifi,Uzgx)","(Iidh,Fpaf)","(Iidh,Meutux)","(Txixz,Ghc)","(Gbclj,Qsc)","(Kgabb,Tuvzkd)","(Uwjsu,Grqrg)","(Vbp,Dwayf)","(Xxmsn,Chhmx)","(Uxf,Uzgx)"};

        // String[] names = {"John(15)","Jon(12)","Chris(13)","Kris(4)","Christopher(19)"};
        // String[] synonyms = {"(Jon,John)","(John,Johnny)","(Chris,Kris)","(Chris,Christopher)"};
        // Csh 39   Wpagta 25/Uhsg 23    Qejo 60   Hvueqc 91
        Map <String, Integer> count = new HashMap<>();
        for(String p :synonyms){
            String l = p.split(",")[0];
            String a = l.substring(1);
            String r = p.split(",")[1];
            String b = r.substring(0, r.length() - 1);

            fmap.put(a, a);
            fmap.put(b, b);
        }
        for(String name: names){
            String nStr = name.split("\\(")[0];
            // int n = Integer.valueOf(name.substring(nStr.length() + 1, name.length()-1));
            // System.out.println(nStr);
            fmap.put(nStr, nStr);
        }
        for(String p :synonyms){
            String l = p.split(",")[0];
            String a = l.substring(1);
            String r = p.split(",")[1];
            String b = r.substring(0, r.length() - 1);
            String fa = find(a);
            String fb = find(b);
//            if(a.equals("Uhsg")&&b.equals("Csh"))
//                System.out.println(fa + " " + fb);
            //System.out.println(a + "的原父亲" + fa);
            //System.out.println(b + "的原父亲" + fb);
            //
            if(fa.compareTo(fb)<0){
                fmap.put(fb, fa);
                //System.out.println(b + "的现父亲" + fa);
            }else{
                fmap.put(fa, fb);
                //System.out.println(a + "的现父亲" + fb);
            }
            // System.out.println(a + " " + b);
        }
        // 25/Uhsg 23    Qejo 60   Hvueqc 91
        // System.out.println(fmap.get("Csh") + " " + fmap.get("Wpagta") + " " + fmap.get("Uhsg") + " " + fmap.get("Qejo") + " " + fmap.get("Hvueqc"));
        // System.out.println(find("Csh") + " " + find("Wpagta") + " " + find("Uhsg") + " " + find("Qejo") + " " + find("Hvueqc"));
        for(String name: names){
            String nStr = name.split("\\(")[0];
            int n = Integer.valueOf(name.substring(nStr.length() + 1, name.length()-1));
            // System.out.println(nStr + " " + n);
            count.put(find(nStr), count.getOrDefault(find(nStr), 0) + n);
        }
        List<String> resList = new ArrayList<>();
        for(Map.Entry e: count.entrySet()){
            // System.out.println(e.getKey() + "(" + e.getValue() + ")");
            resList.add(e.getKey() + "(" + e.getValue() + ")");
        }
        System.out.println(find("Fcclu"));
        String[] ans = {"Fcclu(70)","Dnsay(72)","Qbmk(45)","Unsb(26)","Gauuk(75)","Gnplfi(109)","Hfp(97)","Obcbxb(124)","Ijveol(46)","Fpaf(219)","Qiy(26)","Mcnef(59)","Dhe(79)","Jfq(26)","Ebov(96)","Ofl(72)","Uvkdpn(71)","Avcp(41)","Chycnm(253)","Koaak(53)","Qyqifg(85)","Oltadg(95)","Mwwvj(70)","Naf(3)","Ibink(32)","Ucqh(51)","Mtz(72)","Ard(82)","Hcvcgc(97)","Knpuq(61)","Yeekgc(11)","Ntfr(70)","Bnea(179)","Weusps(79)","Nuq(61)","Drzsnw(87)","Chhmx(259)","Onnev(77)","Kufa(95)","Avmzs(39)","Okwuq(96)","Hljt(51)","Npilye(25)","Axwtno(57)","Kasgmw(95)","Nsgbth(26)","Nzaz(51)","Msyr(211)","Yjc(94)","Jvqg(47)","Alrksy(69)","Aeax(646)","Acohsf(86)","Csh(238)","Nekuam(17)","Kgabb(236)","Fvkhz(104)","Gbkq(77)","Dwifi(237)"};
        System.out.println(count.get("Ijveol"));
    }
}
