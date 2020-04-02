package com.wipro.ta.steps;

import org.springframework.beans.factory.annotation.Autowired;

import com.wipro.ta.context.TestContext;

public abstract class AbstractSteps {
	
	@Autowired
	protected TestContext context;
}
