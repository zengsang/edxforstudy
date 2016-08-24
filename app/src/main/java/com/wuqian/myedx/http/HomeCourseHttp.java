package com.wuqian.myedx.http;

import java.util.Map;

/**
 * Created by wuqian on 2016/5/26.
 */
public class HomeCourseHttp extends BaseHttp {

    static  String data="{\n" +
            "    \"pagination\": {\n" +
            "        \"count\": 10,\n" +
            "        \"previous\": null,\n" +
            "        \"num_pages\": 1,\n" +
            "        \"next\": null\n" +
            "    },\n" +
            "    \"results\": [\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_01+2015_T1\",\n" +
            "            \"name\": \"带上文化去旅行\",\n" +
            "            \"number\": \"Cloudkz_RWKJ_2015_03_01\",\n" +
            "            \"org\": \"Renwenkejixueyuan\",\n" +
            "            \"short_description\": \"       《带上文化去旅行》课程主要选择与自然和人文旅游资源关系紧密的山水、城镇、饮食、建筑、艺术、宗教、民俗等中国传统文化为讲授的主要内容，并适当加入西方文化为补充，以具体旅游资源为例引出丰富多样的各类文化知识，是一门生动有趣的通识教育课。\",\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_01+2015_T1+type@asset+block@旅行.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": \"http://www.youtube.com/watch?v=XMTUwNjQyNzU2MA\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2016-01-30T00:00:00Z\",\n" +
            "            \"start_type\": \"timestamp\",\n" +
            "            \"start_display\": \"2016年1月30日\",\n" +
            "            \"end\": \"2016-04-30T00:00:00Z\",\n" +
            "            \"enrollment_start\": \"2016-01-16T00:00:00Z\",\n" +
            "            \"enrollment_end\": \"2016-03-31T00:00:00Z\",\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3ARenwenkejixueyuan%2BCloudkz_RWKJ_2015_03_01%2B2015_T1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_02+2015_T1\",\n" +
            "            \"name\": \"解构音乐---大学生音乐素养入门\",\n" +
            "            \"number\": \"Cloudkz_RWKJ_2015_03_02\",\n" +
            "            \"org\": \"Renwenkejixueyuan\",\n" +
            "            \"short_description\": \"        本门课程针对的是广大高校学生和音乐爱好者群体，旨在提高学习者的音乐文化素养。本课程是在传统意义上的基本乐理、音乐欣赏、音乐史、音乐美学、和声、曲式分析、复调、配器法等传统音乐课程的基础上建构起来的一门新型课程。本课程的主旨强调提高学习者的音乐文化素养，针对普通学生和音乐爱好者，本课程以艺术文化作为出发点通过音乐基础知识、音乐鉴赏知识以及音乐风格及历史对中外经典音乐新的解析，本课程涉及多门专业课程，但由于立足普及性教育理念，故所涉知识不会过于复杂，因而解构音乐这门课程更是立足传统的新型综合课程。\",\n" +
            "            \"effort\": \"\",\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_02+2015_T1+type@asset+block@音乐02.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2016-01-01T00:00:00Z\",\n" +
            "            \"start_type\": \"timestamp\",\n" +
            "            \"start_display\": \"2016年1月1日\",\n" +
            "            \"end\": \"2016-03-01T00:00:00Z\",\n" +
            "            \"enrollment_start\": \"2016-01-01T00:00:00Z\",\n" +
            "            \"enrollment_end\": \"2016-03-01T00:00:00Z\",\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3ARenwenkejixueyuan%2BCloudkz_RWKJ_2015_03_02%2B2015_T1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_03+2015_T1\",\n" +
            "            \"name\": \"生活中的语言学\",\n" +
            "            \"number\": \"Cloudkz_RWKJ_2015_03_03\",\n" +
            "            \"org\": \"Renwenkejixueyuan\",\n" +
            "            \"short_description\": \"      生活中的语言学，从生活现象中学习语言学知识。\\n      听生动的语言故事，看形象的语言案例，析丰富的语言现象、辨高妙的语言热点……\\n      信息互动、幽默得体，体验社交中的语言魅力；方言语音、歌词妙用，深化对语言的生活认知；文字使用、成语误用，提升规范语言的使用能力……\\n      生动、生活，趣味、品味， 动态、动画，……生活，因语言而精彩！\\n\",\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_03+2015_T1+type@asset+block@生活.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2030-01-01T00:00:00Z\",\n" +
            "            \"start_type\": \"empty\",\n" +
            "            \"start_display\": null,\n" +
            "            \"end\": null,\n" +
            "            \"enrollment_start\": null,\n" +
            "            \"enrollment_end\": null,\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3ARenwenkejixueyuan%2BCloudkz_RWKJ_2015_03_03%2B2015_T1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_04+2015_T1\",\n" +
            "            \"name\": \"批判性思维\",\n" +
            "            \"number\": \"Cloudkz_RWKJ_2015_03_04\",\n" +
            "            \"org\": \"Renwenkejixueyuan\",\n" +
            "            \"short_description\": \"批判性思维是指审慎地运用推理去断定一个断言是否为真（当我们考量某个主意好不好时，就是在进行批判性思维）。培养学生的批判性思维能力已被许多国家确立为教育特别是高等教育的目标之一。本课程就是为培养学生的批判性思维能力而开设的。本课程从批判性思维的重要性和必要性说起，结合丰富的案例就如何进行有效的演绎论证和非演绎推理、如何给出恰当的因果假说和因果解释、如何进行正确的思维和清晰的写作、如何获取有效信息和辨别谬误等批判性思维能力进行了全面的论述，深入阐述了思维内在的逻辑基本规律、需要遵守的逻辑基本法则、需要运用的基本方法等，从而使得学生掌握正确思维和有效思维的基本工具、方法和技能，并学会运用这些工具和方法去合理地提出问题、分析问题和解决问题，养成良好的批判性思维习惯和素养。\",\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_04+2015_T1+type@asset+block@思维.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2030-01-01T00:00:00Z\",\n" +
            "            \"start_type\": \"empty\",\n" +
            "            \"start_display\": null,\n" +
            "            \"end\": null,\n" +
            "            \"enrollment_start\": null,\n" +
            "            \"enrollment_end\": null,\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3ARenwenkejixueyuan%2BCloudkz_RWKJ_2015_03_04%2B2015_T1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_05+2015_T1\",\n" +
            "            \"name\": \"校园足球基础技能\",\n" +
            "            \"number\": \"Cloudkz_RWKJ_2015_03_05\",\n" +
            "            \"org\": \"Renwenkejixueyuan\",\n" +
            "            \"short_description\": \"      本课程是针对普通高校非体育专业学生而开设的公共体育选项课，主要从三个方面学习和了解足球运动的基础。\\n      1、足球运动的基本知识，包括了足球运动概述、足球运动损伤的预防与简易处理、足球竞赛规则以及足球比赛的组织与编排；\\n      2、足球技术的基本技能，它又分为基本技术与基本战术两个部分共18个方面的内容，这是本课程最主要的部分；\\n      3、身体素质练习，包括了速度、灵敏、柔韧、力量及耐力5大基本素质。\\n      本课程以教师为主导，学生为主体，较为系统的通过足球基本技术、基本战术和基础理论以及身体素质的训练与学习，使学生的体质健康和综合素质都得到锻炼和提高，为终身体育打下坚实的基础。同时，以足球训练为载体，让学生能够在足球训练的过程中，体会到不畏强敌、永不放弃、团结协作的体育精神，更希望让学生领悟到通过足球的艰苦训练，磨炼出坚强的意志，为实现振兴中华民族的中国梦奋斗不息!\",\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:Renwenkejixueyuan+Cloudkz_RWKJ_2015_03_05+2015_T1+type@asset+block@足球.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2030-01-01T00:00:00Z\",\n" +
            "            \"start_type\": \"empty\",\n" +
            "            \"start_display\": null,\n" +
            "            \"end\": null,\n" +
            "            \"enrollment_start\": null,\n" +
            "            \"enrollment_end\": null,\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3ARenwenkejixueyuan%2BCloudkz_RWKJ_2015_03_05%2B2015_T1\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:edX+DemoX+Demo_Course\",\n" +
            "            \"name\": \"edX Demonstration Course\",\n" +
            "            \"number\": \"DemoX\",\n" +
            "            \"org\": \"edX\",\n" +
            "            \"short_description\": \"\",\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:edX+DemoX+Demo_Course+type@asset+block@带着文化去旅行.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2013-02-05T05:00:00Z\",\n" +
            "            \"start_type\": \"timestamp\",\n" +
            "            \"start_display\": \"2013年2月5日\",\n" +
            "            \"end\": null,\n" +
            "            \"enrollment_start\": null,\n" +
            "            \"enrollment_end\": null,\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3AedX%2BDemoX%2BDemo_Course\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:clodukz+ball01+123\",\n" +
            "            \"name\": \"足球\",\n" +
            "            \"number\": \"ball01\",\n" +
            "            \"org\": \"clodukz\",\n" +
            "            \"short_description\": \"\",\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:clodukz+ball01+123+type@asset+block@2-2.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2030-01-01T00:00:00Z\",\n" +
            "            \"start_type\": \"empty\",\n" +
            "            \"start_display\": null,\n" +
            "            \"end\": null,\n" +
            "            \"enrollment_start\": null,\n" +
            "            \"enrollment_end\": null,\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3Aclodukz%2Bball01%2B123\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:orgx+cs101+2016_T2\",\n" +
            "            \"name\": \"edX Demonstration Course\",\n" +
            "            \"number\": \"cs101\",\n" +
            "            \"org\": \"orgx\",\n" +
            "            \"short_description\": \"\",\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:orgx+cs101+2016_T2+type@asset+block@带着文化去旅行.png\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2013-02-05T05:00:00Z\",\n" +
            "            \"start_type\": \"timestamp\",\n" +
            "            \"start_display\": \"2013年2月5日\",\n" +
            "            \"end\": null,\n" +
            "            \"enrollment_start\": null,\n" +
            "            \"enrollment_end\": null,\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3Aorgx%2Bcs101%2B2016_T2\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:university+test1+2016_04_01\",\n" +
            "            \"name\": \"test_themes\",\n" +
            "            \"number\": \"test1\",\n" +
            "            \"org\": \"university\",\n" +
            "            \"short_description\": \"主要用来测试课程，看这个课程能测试成功不\",\n" +
            "            \"effort\": \"03:00\",\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:university+test1+2016_04_01+type@asset+block@privateinvestocat.jpg\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": \"http://www.youtube.com/watch?v=3_q8O8xQnlQ\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2016-04-01T09:00:00Z\",\n" +
            "            \"start_type\": \"timestamp\",\n" +
            "            \"start_display\": \"2016年4月1日\",\n" +
            "            \"end\": \"2016-07-01T18:00:00Z\",\n" +
            "            \"enrollment_start\": \"2016-03-01T09:00:00Z\",\n" +
            "            \"enrollment_end\": \"2016-03-31T18:00:00Z\",\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3Auniversity%2Btest1%2B2016_04_01\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"course_id\": \"course-v1:xxxx+xxxx+xxx\",\n" +
            "            \"name\": \"jianx\",\n" +
            "            \"number\": \"xxxx\",\n" +
            "            \"org\": \"xxxx\",\n" +
            "            \"short_description\": null,\n" +
            "            \"effort\": null,\n" +
            "            \"media\": {\n" +
            "                \"course_image\": {\n" +
            "                    \"uri\": \"/asset-v1:xxxx+xxxx+xxx+type@asset+block@images_course_image.jpg\"\n" +
            "                },\n" +
            "                \"course_video\": {\n" +
            "                    \"uri\": null\n" +
            "                }\n" +
            "            },\n" +
            "            \"start\": \"2030-01-01T00:00:00Z\",\n" +
            "            \"start_type\": \"empty\",\n" +
            "            \"start_display\": null,\n" +
            "            \"end\": null,\n" +
            "            \"enrollment_start\": null,\n" +
            "            \"enrollment_end\": null,\n" +
            "            \"blocks_url\": \"http://edx.dev.cloudkz.cn:81/api/courses/v1/blocks/?course_id=course-v1%3Axxxx%2Bxxxx%2Bxxx\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    @Override
    protected boolean isForverSave() {
        return true;
    }

    @Override
    protected int getParamType() {
        return BaseHttp.PARAM_TYPE_BODY;
    }

    @Override
    protected Map<String, String> getParams() {
        return null;
    }

    @Override
    protected String getFileName() {
        return "home_course";
    }

    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    protected String getMethod() {
        return null;
    }
}
