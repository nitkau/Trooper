/*
 * Copyright 2012-2015, the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trpr.platform.batch.common;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.trpr.platform.batch.impl.spring.web.JobController;

/**
 * The <code>JobInfo</code> class is an extension of {@link JobInfo} that stores extra information about 
 * trigger, such as cronexpression, next fire time, hostnames, etc.
 * 
 * @author devashishshankar
 * @version 1.0, 09 Jan 2013
 */
public class JobInfo extends org.springframework.batch.admin.web.JobInfo {

	/** The Date format*/
	private static final FastDateFormat DATE_FORMAT = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT;

	/** Holds the Cron Expression of the job trigger */
	private final String cronExpression;

	/** Holds the next fire time of the job */
	private final Date nextFireTime;

	/** Holds the hostname of the job */
	private List<JobHost> hostNames;

	/**
	 * Constructor for initialising JobInfo
	 */
	public JobInfo(String name, int executionCount, Long jobInstanceId,
			boolean launchable, boolean incrementable, String cronExpression, Date nextFireTime) {		
		//Calling super constructor to initialise the remaining attributes
		super(name, executionCount, jobInstanceId, launchable, incrementable);
		this.cronExpression = cronExpression;
		this.nextFireTime = nextFireTime;	
		this.hostNames = new LinkedList<JobHost>();
	}

	/** Getter/setter Methods */
	public void addHost(JobHost hostName) {
		this.hostNames.add(hostName);
	}

	public void removeHost(JobHost hostName) {
		if(this.hostNames.contains(hostName)) {
			this.hostNames.remove(hostName);
		}
	}

	public List<JobHost> getHostNames() {
		return this.hostNames;
	}

	public String getcronExpression() {
		return this.cronExpression;
	}	

	public String getnextFireTime() {
		if(this.nextFireTime==null) 
			return JobController.NOT_FOUND;
		return DATE_FORMAT.format(this.nextFireTime);
	}
	/** End getter/setter methods */
}
