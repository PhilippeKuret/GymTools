package com.example.philippe.gymtools.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(tableName = "workout", foreignKeys = @ForeignKey(entity =
															Exercise.class,
															onDelete = CASCADE,
															parentColumns = "id",
															childColumns = "exercise_id"))
public class Workout implements Parcelable
{
	public Workout()
	{
	}

	public Workout(int id, String name, double weight,int exerciseId)
	{
		Id = id;
		Name = name;
		Weight = weight;
		this.exerciseId = exerciseId;
	}

	@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
	private int Id;

	@ColumnInfo(name = "name")
	private String Name;

	@ColumnInfo(name = "weight")
	private double Weight;

	@ColumnInfo(name = "exercise_id")
	private int exerciseId;

	public int getId()
	{
		return Id;
	}

	public void setId(int id)
	{
		Id = id;
	}

	public String getName()
	{
		return Name;
	}

	public void setName(String name)
	{
		this.Name = name;
	}

	public double getWeight()
	{
		return Weight;
	}

	public void setWeight(double weight)
	{
		Weight = weight;
	}

	public int getExerciseId()
	{
		return exerciseId;
	}

	public void setExerciseId(int exerciseId)
	{
		this.exerciseId = exerciseId;
	}

	protected Workout(Parcel in)
	{
		Id = in.readInt();
		Name = in.readString();
		Weight = in.readDouble();
		exerciseId = in.readInt();
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(Id);
		dest.writeString(Name);
		dest.writeDouble(Weight);
		dest.writeInt(exerciseId);
	}

	public static final Creator<Workout> CREATOR = new Creator<Workout>()
	{
		@Override
		public Workout createFromParcel(Parcel in)
		{
			return new Workout(in);
		}

		@Override
		public Workout[] newArray(int size)
		{
			return new Workout[size];
		}
	};
}
