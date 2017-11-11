package com.example.philippe.gymtools.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "set")
public class Set
{
	public Set(int id, double weight, int repetition)
	{
		Id = id;
		Weight = weight;
		Repetition = repetition;
	}

	@PrimaryKey @ColumnInfo(name = "id")
	private int Id;

	@ColumnInfo(name = "weight")
	private double Weight;

	@ColumnInfo(name = "repetition")
	private int Repetition;

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

	public double getWeight()
	{
		return Weight;
	}

	public void setWeight(double weight)
	{
		Weight = weight;
	}

	public int getRepetition()
	{
		return Repetition;
	}

	public void setRepetition(int repetition)
	{
		Repetition = repetition;
	}
}
