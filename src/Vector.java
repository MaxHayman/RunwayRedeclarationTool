public class Vector {
  public int x;
  public int y;
  public int z;

  public Vector(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Vector add(Vector addend) {
    return new Vector(x + addend.x, y + addend.y, z + addend.z);
  }

}