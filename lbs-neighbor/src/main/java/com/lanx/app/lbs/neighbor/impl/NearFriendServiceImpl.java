package com.lanx.app.lbs.neighbor.impl;

import java.io.Serializable;

import org.hsqldb.rights.User;
import org.springframework.data.domain.Page;

import com.lanx.app.lbs.core.Accuracy;
import com.lanx.app.lbs.neighbor.NearFriendService;
import com.lanx.app.lbs.util.LBSConstants.SortType;

public class NearFriendServiceImpl implements NearFriendService{

	@Override
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(double longitude, double latitude) {
		return this.findNearFriendUids(longitude, latitude,  Accuracy.ONE_KM);
	}

	@Override
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(double longitude, double latitude, Accuracy accuracy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <UID extends Serializable> Page<UID> findNearFriendUids(double longitude, double latitude, Accuracy accuracy, int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <UID extends Serializable> Iterable<UID> findNearFriendUids(String pid, Accuracy accuracy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <UID extends Serializable> Page<UID> findNearFriendUids(String pid,Accuracy accuracy, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findNearFriends(double longitude, double latitude) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findNearFriends(double longitude, double latitude,Accuracy accuracy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findNearFriends(double longitude, double latitude,Accuracy accuracy, int pageNo, int pageSize, SortType sortType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findNearFriends(String pid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<User> findNearFriends(String pid, Accuracy accuracy) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findNearFriends(String pid, Accuracy accuracy,int pageNo, int pageSize, SortType sortType) {
		// TODO Auto-generated method stub
		return null;
	}

}
