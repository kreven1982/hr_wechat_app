package com.cognizant.cdc.controller

import javax.servlet.http.HttpServletRequest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

import com.cognizant.cdc.model.Content
import com.cognizant.cdc.model.Job

@Controller
@RequestMapping(value = "/content")
class ContentController {

    List<Content> sampleContents = [
            new Content(id: 1, title: "About Cognizant", content: "Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest growing companies in the world"),
			new Content(id: 2, title: "About Cognizant", content: "Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest growing companies in the world"),
			new Content(id: 3, title: "About Cognizant", content: "Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest growing companies in the world"),
			new Content(id: 4, title: "About Cognizant", content: "Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest growing companies in the world"),
			new Content(id: 5, title: "About Cognizant", content: "Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest growing companies in the world"),
			new Content(id: 6, title: "About Cognizant", content: "Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest growing companies in the world"),
			new Content(id: 7, title: "About Cognizant", content: "Headquartered in Teaneck, New Jersey (U.S.), Cognizant combines a passion for client satisfaction, technology innovation, deep industry and business process expertise,and a global, collaborative workforce that embodies the future of work. With over 50 delivery centers worldwide and approximately 187,400 employees as of June 30, 2014, Cognizant is a member of the NASDAQ-100, the S&P 500, the Forbes Global 2000, and the Fortune 500 and is ranked among the top performing and fastest growing companies in the world"),
    ]

    @RequestMapping(value="list", method = RequestMethod.GET)
    public ModelAndView listContents() {

        List<Content> contents = sampleContents;

        ModelAndView modelAndView = new ModelAndView("management/contentList")
        modelAndView.addObject("contents", contents)

        return modelAndView
    }

    @RequestMapping(value="view", method = RequestMethod.GET)
    public String showDetail() {
        return "management/content"
    }
	
	@RequestMapping(value="search", method = RequestMethod.GET)
	public String search() {
		return "management/contentSearch"
	}

    @RequestMapping(value="new", method = RequestMethod.GET)
    public String toNewContent(HttpServletRequest request) {
        return "management/contentNew"
    }
}
